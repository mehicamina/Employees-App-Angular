import 'gojs';

import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import * as go from 'gojs';
import { MenuService } from 'src/app/menu.service';

import { EmployeInfo } from '../classes/employeInfo';
import { MenuItem } from '../classes/menuitems';

@Component({
  selector: 'app-tree',
  templateUrl: './tree.component.html',
  styleUrls: ['./tree.component.css']
})
export class TreeComponent implements OnInit {
  @Output() someEvent = new EventEmitter<any>();
  messege: EmployeInfo;
  items: MenuItem[];
  projectShear: string;
  constructor(private menuService: MenuService) { }
  private $;
  private myDiagram;
  public activitys = Array<any>();
  createGraph() {
    this.$ = go.GraphObject.make;
    this.myDiagram =
      this.$(go.Diagram, 'myDiagramDiv',
        {
          maxSelectionCount: 1,
          'undoManager.isEnabled': true,
          layout: this.$(go.TreeLayout,
            { angle: 90, layerSpacing: 35 },
          )
        });
    this.myDiagram.nodeTemplate =
      this.$(go.Node, 'Horizontal',
        { background: '#313536' },
        {
          click: (e, obj) => {
            const clicked = obj.part;
            this.messege.id = clicked.data.id;
            this.messege.image = clicked.data.source;
            if (clicked !== null) {
              this.menuService.changeEmployeeInfo(this.messege);
              this.someEvent.next('selected');
            }
          }
        },
        this.$(go.Picture,
          { margin: 10, width: 50, height: 50 },
          new go.Binding('source')
        ),
        this.$(go.Panel, 'Table',
          // this.$(go.Shape, "Circle", { fill: "lightgreen" }),
          {
            maxSize: new go.Size(300, 999),
            margin: new go.Margin(6, 10, 0, 3),
            defaultAlignment: go.Spot.Left
          },
          this.$(go.RowColumnDefinition, { column: 2, width: 4 }),
          this.$(go.TextBlock, 'Default Text',
            {
              margin: 3, row: 0, column: 0, columnSpan: 5, alignment: go.Spot.Center, stroke: '#adadad', font: '16px Incised'
            },
            new go.Binding('text', 'name')),
          this.$(go.TextBlock, 'Default Text',
            { margin: 3, row: 1, column: 1, columnSpan: 5, stroke: 'white', font: '18px Incised' },
            new go.Binding('text', 'title')))
      );
    this.myDiagram.linkTemplate =
      this.$(go.Link,
        { routing: go.Link.Orthogonal, corner: 5 },
        this.$(go.Shape, { strokeWidth: 3, stroke: '#555' }));
    this.myDiagram.nodeTemplate.contextMenu =
      this.$('ContextMenu',
        this.$('ContextMenuButton',
          this.$(go.TextBlock, 'Vacate Position'),
          {
            click: (e, obj) => {
              const node = obj.part;
              if (node !== null) {
                const thisemp = node.data;
                this.myDiagram.startTransaction('vacate');
                this.myDiagram.model.setDataProperty(thisemp, name, '(Vacant)');
                this.myDiagram.model.setDataProperty(thisemp, 'comments', '');
                this.myDiagram.commitTransaction('vacate');
              }
            }
          }
        ),

      );
  }
  ngOnInit() {
    this.menuService.getActivity()
      .subscribe(data => {
        this.activitys = data['payload'];
      });
    this.menuService.empInf.subscribe(emess => this.messege = emess);
    this.createGraph();
    this.showGraph('BHT');
  }

  showGraph(project: string) {
    const model = this.$(go.TreeModel);
    this.projectShear = project;
    this.menuService.projectEmployee(project)
      .subscribe(data => {
        model.nodeDataArray = data['payload'];
        this.myDiagram.model = model;
      });
  }

}
