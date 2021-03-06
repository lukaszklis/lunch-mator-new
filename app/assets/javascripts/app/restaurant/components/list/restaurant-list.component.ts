import {Component, OnInit, Injectable} from '@angular/core';
import {RestaurantDto} from '../../../dto/types';
import {RestaurantService} from '../../services/restaurant.services';
import {AlertDisplay} from '../../../common/services/AlertDisplay';
import {AlertLevel} from '../../../common/types/Alert';
import {Router} from '@angular/router';

@Component({
  selector: 'restaurant-list',
  templateUrl: 'assets/javascripts/app/restaurant/components/list/restaurant-list.component.html'
})

@Injectable()
export class RestaurantListComponent extends AlertDisplay implements OnInit {
  restaurantList: RestaurantDto[];
  waiting: boolean;

  constructor(private router: Router, private restaurantService: RestaurantService) {
    super();
  }

  ngOnInit(): void {
    this.getRestaurants();
  }

  public start(restaurant: RestaurantDto) {
    this.router.navigateByUrl(`/add-lunch?restaurantId=${restaurant.id}`)
  }

  private getRestaurants() {
    this.waiting = true;
    this.restaurantService.getAllRestaurants()
      .subscribe((response: RestaurantDto[]) => {
        this.restaurantList = response;
        this.waiting = false;
      }, (error: any) => {
        this.waiting = false;
        this.displayAlert(AlertLevel.ERROR, `Error retrieving available lunch: [${error}]`, 5)
      });
  }
}
