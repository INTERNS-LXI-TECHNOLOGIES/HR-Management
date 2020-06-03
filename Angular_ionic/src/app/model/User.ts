export class User{
    id: number;
    firstName: string;
    lastName: string;
    Position: string;

    constructor(values: Object = {}) 
    {
        Object.assign(this, values);
    }
}
export interface userViewModel{

    firstName:string;
    lastName:string;
    company:string;
    email:string;
    position:string;
    authorities:string;  
    joiningDate;
    dob;
    image;
    login:string;
    password:string;
  
  }