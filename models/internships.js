const mongoose = require('mongoose');
// const bcrpyt = require('bcrpytjs');
const assert = require('assert')
const config = require('../config/database');

// User schema 
const InternshipSchema = mongoose.Schema({
	internship:{
		type: String,
		required: true
	}
});


const Internship = module.exports = mongoose.model('Internship',InternshipSchema);

// module.exports.getExperience = function(id,callback){
// 	Jobex.findById(id,callback);
// }
module.exports.getInternship = function(callback){
	console.log("hello");
	// console.log(Jobexp.collection.find());
	// console.log(Jobexp.find({}));
	// Jobexp.find({});
	// console.log("hello");
	Internship.find(function (err, results) {
		console.log(results);
        assert.equal(null, err);
        
        //invoke callback with your mongoose returned result
        callback(err,results);
      });
}
// module.exports.getExperience = function(username, callback){
// 	const query = {username:username};
// 	User.findOne(query,callback);
// }

module.exports.addInternship = function(newInternship , callback){
	newInternship.save(callback);
}

