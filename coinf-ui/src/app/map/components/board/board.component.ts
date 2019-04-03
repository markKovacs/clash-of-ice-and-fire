import { Component, OnInit } from '@angular/core';
import { BOARD_DATA } from './board-data';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  boardData = BOARD_DATA;

  constructor() {}

  ngOnInit() {}

  onStark(event: any) {
    console.log('Stark:', event);
  }

}
