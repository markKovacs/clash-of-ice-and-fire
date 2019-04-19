import { Component, Input } from '@angular/core';
import { Hexagon } from '../../model/hexagon-data';

@Component({
  selector: 'app-hexagon',
  templateUrl: './hexagon.component.html',
  styleUrls: ['./hexagon.component.css']
})
export class HexagonComponent {

  @Input() hexData: Hexagon;

  onCharacter(event: any) {
    console.log(event);
  }

  getImage(name: string, extension: string): string {
    return `assets/images/${name}.${extension}`;
  }

}
