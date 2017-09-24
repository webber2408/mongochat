const express = require('express');
const router = express.Router();
const passport = require('passport');
const jwt = require('jsonwebtoken');
const Jobexp = require('../models/jobexp');
const config = require('../config/database');

//register 

router.post('/addExperience' , (req,res , next) => {
	// res.send("Register !");
	let newExperience = new Jobexp({
		companyName: req.body.companyName,
        experience:req.body.experience
	});

	Jobexp.addExperience(newExperience , (err,newExperience)=>{
		if(err){
			res.json({
				success:false,
				msg: 'Failed to add experience !'
			});
		}
		else{
			res.json({
				success: true,
				msg: 'Experience recorded successfully  !'
			});
		}
	});
});

router.get('/getexperience' , (req,res) => {
	// res.send("Register !");
	console.log("hello");
	Jobexp.getExperience( (err,results)=>{
		console.log("hello");
		console.log(results);
		if(err){
			res.json({
				success:false,
				msg: 'Failed to retrieve experience !'
			});
		}
		else{
			res.json({
				success: true,
				msg: 'All experience retrieved !',
				results:results
			});
		}
	});
});






module.exports = router;