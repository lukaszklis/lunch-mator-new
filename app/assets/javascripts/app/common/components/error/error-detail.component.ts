import {Component, Input} from '@angular/core';
import {ErrorDetail} from '../../types/ErrorDetail';

@Component({
  selector: 'error-detail',
  templateUrl: 'assets/javascripts/app/common/components/error/error-detail.component.html'
})

export class ErrorDetailComponent {
  @Input()
  error: ErrorDetail;
}

