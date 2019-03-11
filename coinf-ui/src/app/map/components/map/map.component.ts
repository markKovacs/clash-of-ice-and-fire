import { Component, OnInit } from '@angular/core';
import * as SVG from 'svg.js';
import * as Honeycomb from 'honeycomb-grid';
import { tiles } from './tiles.constant';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  Grid: any;
  grid: any;
  tiles = tiles;

  constructor() {}

  ngOnInit(): void {

    const getColor = (x, y) => {
      return this.tiles[`${x}-${y}`].color;
    };

    const drawSVG = SVG('drawMap');

    const Hex = Honeycomb.extendHex({
      size: 30,
      orientation: 'flat',

      render(draw) {
        const position = this.toPoint();
        const centerPosition = this.center().add(position);
        const corners = this.corners();

        this.draw = draw
          .polygon(corners.map(({ x, y }) => `${x},${y}`))
          .fill(getColor(this.x, this.y))
          .stroke({ width: 1, color: '#999' })
          .translate(position.x, position.y);

        // draw x and y coordinates
        const fontSize = 12;
        draw.text(`${this.x},${this.y}`)
            .font({
              size: fontSize,
              anchor: 'middle',
              leading: 1.4,
              fill: '#69c'
            })
            .translate(centerPosition.x, centerPosition.y - fontSize);
      },

      highlight() {
        this.draw
          // stop running animation
          .stop(true, true)
          .fill({ opacity: 1, color: 'aquamarine' })
          .animate(300)
          .fill({ opacity: 0 })
          .fill({ color: getColor(this.x, this.y) })
          .animate(300)
          .fill({ opacity: 1 });
      }
    });
    this.Grid = Honeycomb.defineGrid(Hex);
    this.grid = this.Grid.rectangle({
      width: 5,
      height: 8,
      // render each hex, passing the draw instance
      onCreate(hex) {
        hex.render(drawSVG);
      }
    });

  }

  highlight(event) {
    const { offsetX, offsetY } = event;
    const hexCoordinates = this.Grid.pointToHex([offsetX, offsetY]);
    const hex = this.grid.get(hexCoordinates);
    console.log(hex);
    if (hex) {
      hex.highlight();
    }
  }

}
