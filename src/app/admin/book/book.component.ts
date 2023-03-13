import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/services/book.service';


declare var $:any;

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent{

  errorMessage:string = "";
 
 @Input() book:Book = new Book();
  
  
  @Output() save = new EventEmitter<any>();
  constructor(private bookService : BookService) { }


  saveBook(){
    this.bookService.saveBook(this.book).subscribe({next: data =>
      {
        //...
       // this.book = data
       this.save.emit(data);
       $('#bookmodal').modal('hide');
      },error:err =>{
        this.errorMessage ='Unexpected Error Occured.';
        console.log(err);
      }})
  }

  showBookModal(){
    $('#bookmodal').modal('show');
  }
}
