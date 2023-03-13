import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/model/book';
import { PurchaseHistory } from 'src/app/model/purchasehistory';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { BookService } from 'src/app/services/book.service';
import { PurchaseService } from 'src/app/services/purchase.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

bookList:Array<Book> =[];

errorMessage:string="";
infoMessage:string ="";


  constructor(private authenticationService :AuthenticationService,
    private bookService :BookService,
    private purchaseService:PurchaseService) { }

  ngOnInit(): void {

    this.bookService.getAllBooks().subscribe({
      next:data=>{
        this.bookList =data;
      }
    })
  }

purchase(item:Book){
  if(!this.authenticationService.currentUserValue?.id){
    this.errorMessage ="you should log in for purchase";
    console.log(this.errorMessage);
    return;
  }

  const purchase = new PurchaseHistory(this.authenticationService.currentUserValue.id,item.id,item.price);

  this.purchaseService.savePuurchase(purchase).subscribe({
    next: data => {
      this.infoMessage ="Purchase done";
    },error :err =>{
      this.errorMessage="unexpected error";
      console.log(err);
    }
  })
}


}
