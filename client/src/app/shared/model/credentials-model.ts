export class Credentials {
    id : number;
    userName : string;
    passWord : string;
    webSite : string;
    activeStatus : string;
    actionLink : string;

    constructor(unm : string, pwd : string, wst : string, ast : string, alk : string) {
        this.userName = unm;
        this.passWord = pwd;
        this.webSite = wst;
        this.activeStatus = ast;
        this.actionLink = alk;
    };
}