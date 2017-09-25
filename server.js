const mongo = require('mongodb').MongoClient;
const client = require('socket.io').listen(4000).sockets;

// Connect to mongo
mongo.connect('mongodb://localhost:27017/meanauth', function(err, db){
    if(err){
        throw err;
    }

    console.log('MongoDB connected...');

    // Connect to Socket.io
    client.on('connection', function(socket){
        console.log(socket.handshake.address)
        let chat = db.collection('chats');

        // Create function to send status
        sendStatus = function(s){
            socket.emit('status', s);
        }

        // Get chats from mongo collection
        chat.find().limit(100).sort({_id:1}).toArray(function(err, res){
            if(err){
                throw err;
            }

            // Emit the messages
            // console.log(res);
            client.emit('updatedMessages', res);
        });
       
        var updateMessages = function(){
            // Get chats from mongo collection
            chat.find().limit(100).sort({_id:1}).toArray(function(err, res){
                if(err){
                    throw err;
                }

                // Emit the messages
                console.log(res);
                client.emit('updatedMessages', res);
            });
        }

        // Handle input events
        socket.on('input', function(data){
            //console.log(data);
            // console.log(data.username);
            let name = data.username;
            let message = data.message;
            //console.log("hello");
            //console.log(name+message);
            // Check for name and message
            if(name == '' || message == ''){
                // Send error status
                sendStatus('Please enter a name and message');
            } else {
                // Insert message
                chat.insert({username: name, message: message}, function(){
                    client.emit('updatedMessages', [data]);

                    // Send status object
                    sendStatus({
                        message: 'Message sent',
                        clear: true
                    });
                });
            }
            //updateMessages();
        });

        // Handle clear
        socket.on('clear', function(data){
            // Remove all chats from collection
            chat.remove({}, function(){
                // Emit cleared
                socket.emit('cleared');
            });
        });
    });
});