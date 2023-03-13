import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PurchaseHistory } from '../model/purchasehistory';
import { AuthenticationService } from './authentication.service';
import { CommonService } from './common.service';
const API_URL = `${environment.BASE_URL}/api/purchase-history`;

@Injectable({
  providedIn: 'root'
})
export class PurchaseService extends CommonService{

  constructor(authenticationService :AuthenticationService ,http:HttpClient) {
    super(authenticationService,http);
   }



   savePuurchase(purchase:PurchaseHistory):Observable<any>{
    return this.http.post(API_URL,purchase,{headers:this.getHeaders});
   }

   getAllPurchaseItems():Observable<any>{
    return this.http.get(API_URL,{headers:this.getHeaders});

   }
}
