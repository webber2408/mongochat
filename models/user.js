const mongoose = require('mongoose');
// const bcrpyt = require('bcrpytjs');
const config = require('../config/database');

// User schema 
const UserSchema = mongoose.Schema({
	name:{
		type: String,
		required: true
	},
	username:{
		type:String,
		required: true
	},
	email:{
		type: String,
		required:true
	},
	password:{
		type:String,
		required:true
	},
	contact:{
		type:Number,
		required:true
	}
});


const User = module.exports = mongoose.model('User',UserSchema);

module.exports.getUserById = function(id,callback){
	User.findById(id,callback);
}

module.exports.getUserByUsername = function(username, callback){
	const query = {username:username};
	User.findOne(query,callback);
}

module.exports.addUser = function(newUser , callback){
	newUser.save(callback);
}

