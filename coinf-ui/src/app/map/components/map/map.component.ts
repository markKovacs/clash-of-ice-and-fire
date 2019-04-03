import { Component, OnInit } from '@angular/core';
import { tiles } from './tiles.constant';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  constructor() {}

  ngOnInit() {}

  onStark(event: any) {
    console.log('Stark:', event);
  }

}
