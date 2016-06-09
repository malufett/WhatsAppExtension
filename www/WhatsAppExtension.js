var exec = require('cordova/exec');
var Controller = {};

Controller.executeCMD = function (cmd, parameter, successCallback, errorCallback) {
    exec(success, error, "WhatsAppExtension", cmd, [parameter]);
};

module.exports = Controller;