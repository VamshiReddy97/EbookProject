export class PurchaseHistory {

    id:number |undefined;
    userId:number |undefined;
    bookId:number |undefined;
    price:number |undefined;
    purchaseTime :Date = new Date();


    constructor(userID?:number, bookId?: number, price?: number){
        this.userId=userID;
        this.bookId=bookId;
        this.price=price;
    }
}
