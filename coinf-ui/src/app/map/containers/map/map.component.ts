import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { Foo } from '../../../shared/models/foo.interface';
import { Region } from '../../../shared/models/region.interface';
import { Observable } from 'rxjs';
import * as fromRoot from '../../../core/store/app.reducer';
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  private foo: Foo;
  private regions: Region[];

  constructor(
    private authService: AuthService,
    private store: Store<fromRoot.State>
  ) {}

  ngOnInit(): void {
    this.authService.getRegions()
      .subscribe(
        data => this.regions = data,
        error => console.log(error)
      );
  }

  getFoo() {
    this.authService.getFoo(1)
      .subscribe(
        data => this.foo = data,
        error => console.log(error)
      );
  }

}