export class User {
    id : number;
    userName : string;
    passWord : string;

    constructor(unm : string, pwd : string) {
        this.userName = unm;
        this.passWord = pwd;
    };
}