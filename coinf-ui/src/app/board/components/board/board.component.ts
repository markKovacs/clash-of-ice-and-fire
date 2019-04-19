import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  @Input() boardData;

  constructor() {}

  ngOnInit() {}

  onStark(event: any) {
    console.log('Stark:', event);
  }

}
