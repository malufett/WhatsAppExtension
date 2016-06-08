var Controller = {};

Controller.executeCMD = function (cmd, parameter, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "whatsapp", cmd, [parameter]);
};

module.exports = Controller;
