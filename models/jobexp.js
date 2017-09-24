const mongoose = require('mongoose');
// const bcrpyt = require('bcrpytjs');
const assert = require('assert')
const config = require('../config/database');

// User schema 
const JobexSchema = mongoose.Schema({
	companyName:{
		type: String,
		required: true
	},
	experience:{
		type:String,
		required: true
	}
});


const Jobexp = module.exports = mongoose.model('Jobexp',JobexSchema);

// module.exports.getExperience = function(id,callback){
// 	Jobex.findById(id,callback);
// }
module.exports.getExperience = function(callback){
	console.log("hello");
	// console.log(Jobexp.collection.find());
	// console.log(Jobexp.find({}));
	// Jobexp.find({});
	// console.log("hello");
	Jobexp.find(function (err, results) {
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

module.exports.addExperience = function(newExperience , callback){
	newExperience.save(callback);
}

