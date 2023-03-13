import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { PurchaseItem } from 'src/app/model/purchase-item';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { PurchaseService } from 'src/app/services/purchase.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

purchaseItemList :Array<PurchaseItem> =[];

  constructor(private purchaseService :PurchaseService) { }

  ngOnInit(): void {


    this.purchaseService.getAllPurchaseItems().subscribe({
      next: data => {
        this.purchaseItemList=data;
      }
    });
  }


}


