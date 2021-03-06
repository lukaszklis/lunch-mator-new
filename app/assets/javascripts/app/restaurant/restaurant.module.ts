import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {AddRestaurantComponent} from './components/addrestaurant/add-restaurant.component';
import {Ng2CompleterModule} from 'ng2-completer';
import {routing} from './restaurant.routing';
import {RestaurantService} from './services/restaurant.services';
import {CommonModules} from '../common/common.modules';
import {RestaurantListComponent} from './components/list/restaurant-list.component';
import {RestaurantItemComponent} from './components/item/restaurant-item.component';

@NgModule({
  imports: [CommonModule, FormsModule, Ng2CompleterModule, CommonModules, routing],
  declarations: [AddRestaurantComponent, RestaurantListComponent, RestaurantItemComponent],
  providers: [RestaurantService]
})
export class RestaurantModule {
}
