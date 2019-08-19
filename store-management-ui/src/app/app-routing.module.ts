import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StoreListComponent } from './store-list/store-list.component';
import { StoreDetailsComponent } from './store-details/store-details.component';
import { StoreAddressComponent } from './store-address/store-address.component';

const routes: Routes = [
  { path: '', component: StoreListComponent },
  { path: 'stores/:storeId', component: StoreDetailsComponent },
  { path: 'store-address', component: StoreAddressComponent },
  { path: 'detail', component: StoreDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
