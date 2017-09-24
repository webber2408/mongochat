var JwtStrategy = require('passport-jwt').Strategy,
    ExtractJwt = require('passport-jwt').ExtractJwt;
    
// const passport = require('passport');
const User = require('../models/user');
const jwt = require('jsonwebtoken');
const config = require('../config/database');


module.exports = function(passport){
	var opts = {};
	console.log("hello");
	opts.jwtFromRequest = ExtractJwt.fromAuthHeaderWithScheme('jwt');
	console.log(opts);
	// opts.jwtFromRequest = ExtractJwt.fromAuthHeaderAsBearerToken();
	opts.secretOrKey = config.secret;
	console.log("hello");
	passport.use(new JwtStrategy(opts, function(jwt_payload, done) {
		console.log("hello");
		console.log(jwt_payload);
		console.log(jwt_payload._doc);
		console.log(jwt_payload.data._id);
			User.findOne({_id: jwt_payload.data._id}, function(err, user) {

				if(err){
					// console.log("test1");
					return done(err,false);
				}
				if(user){
					// console.log("test2");
					return done(null , user);
				}
				else{
					// console.log("test3");
					return done(null, false);
				}
			});
	}));
}