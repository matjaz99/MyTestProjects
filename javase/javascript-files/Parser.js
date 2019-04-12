/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function sestej(x) {
    return x + 3;
}

function parseAlarm(param) {
    var t = "ok " + param.getTrap();
    var response = new Object();
    param.setText(t);
    param.setSeverity(2);
    var u = myctx.getSomeCtx();
    u = sestej(u);
    param.setTimestamp(u);
    return param;
}
