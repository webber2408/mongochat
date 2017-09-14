const express = require('express');
const path = require('path');

//parses incoming request body
const bodyParser = require('body-parser');
const cors = require('cors');
const passport = require('passport');
const mongoose = require('mongoose');
const config = require('./config/database');


//database connection
mongoose.Promise = global.Promise;
mongoose.connect(config.database);

// on databse connection 
mongoose.connection.on('connected',()=>{
	console.log('Connected to database :'+config.database);
});

// on error 
mongoose.connection.on('error',(err)=>{
	console.log('Databse error :'+err);
});


const app= express();
const users = require('./routes/users');

const port = 3000;

app.use(cors());
//to disable some routes if user doesnt send correct tokens

//set static folder 
app.use(express.static(path.join(__dirname , 'public')));

//Body parser middleware 
// parses / grabs incoming form data etc ...
app.use(bodyParser.json());
app.use('/users',users);

//Passport middleware
app.use(passport.initialize());
app.use(passport.session());

require('./config/passport')(passport);

//index route
app.get('/', (req,res)=> {
res.send('Invalid Endpoint !');
});


//start server
app.listen(port,() => {
	console.log("Server started on port : "+port);
});