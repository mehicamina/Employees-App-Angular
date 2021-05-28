export class User {
    userName: string;
    pasword: string;

    constructor(name: string, pass: string) {
        this.userName = name;
        this.pasword = pass;
    }
    }

export class LoginBolleans {
        static header = false;
        static side = false;

        static getHeader(): boolean {
            return this.header;
        }
        static getSide(): boolean {
            return this.header;
        }

        static setHeader(param: boolean) {
            this.header = param;
        }
        static setSide(param: boolean) {
            this.side = param;
        }
    }
