const express = require('express');
const router = express.Router();
const passport = require('passport');
const jwt = require('jsonwebtoken');
const Internship = require('../models/internships');
const config = require('../config/database');

//register 

router.post('/addInternship' , (req,res , next) => {
	// res.send("Register !");
	let newInternship = new Internship({
		internship: req.body.internship
	});

	Internship.addInternship(newInternship , (err,newInternship)=>{
		if(err){
			res.json({
				success:false,
				msg: 'Failed to add internship !'
			});
		}
		else{
			res.json({
				success: true,
				msg: 'Internship recorded successfully  !'
			});
		}
	});
});

router.get('/getInternship' , (req,res) => {
	// res.send("Register !");
	console.log("hello");
	Internship.getInternship( (err,results)=>{
		console.log("hello");
		console.log(results);
		if(err){
			res.json({
				success:false,
				msg: 'Failed to retrieve internships !'
			});
		}
		else{
			res.json({
				success: true,
				msg: 'All internships retrieved !',
				results:results
			});
		}
	});
});






module.exports = router;