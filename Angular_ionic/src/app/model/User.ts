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