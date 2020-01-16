import { Component, OnInit} from '@angular/core';
import { ChartDataSets, ChartOptions,ChartType } from 'chart.js';
import { SingleDataSet,MultiDataSet ,Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip,Color } from 'ng2-charts';
import { StatisticsService } from  './statistics.service'


@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {


Linechart = [];
 datos = Array<any>();
 stat: any;
x= [];
y=[];

  ngOnInit() {
      
  }

  constructor( public statservice: StatisticsService ) { 
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();


  }

  

  years = [2019,2020,2021,2022,2023,2024]


  lineChartData: ChartDataSets[] = [ { data: [0, 0, 0, 0, 0, 0], label: 'incidencias mensuales' },];
  lineChartLabels: Label[] = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio'];
  lineChartOptions = {responsive: true,};
  lineChartColors: Color[] = [ { borderColor: 'black', backgroundColor: 'rgba(13,10,178,0.44)', },];
  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType = 'line';



  public pieChartOptions: ChartOptions = {responsive: true,};
  public pieChartLabels: Label[] = ['SciFi', 'Drama', 'Comedy', 'Action','Animation'];
  public pieChartData: SingleDataSet = [15, 15, 40, 20,10];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];


  barChartOptions: ChartOptions = {responsive: true,};
  barChartLabels: Label[] = ['Apple', 'Banana', 'Kiwifruit', 'Blueberry', 'Orange', 'Grapes'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  barChartData: ChartDataSets[] = [ { data: [45, 37, 60, 70, 46, 33], label: 'Best Fruits' }];
 
 
 
  doughnutChartLabels: Label[] = ['BMW', 'Ford', 'Tesla'];
  doughnutChartData: MultiDataSet = [ [55, 25, 20] ];
  doughnutChartType: ChartType = 'doughnut';



  data(year:number){

    console.log(year)
    this.statservice.getStats(year).subscribe( (data) => {
      this.stat = data;
      console.log (this.stat);

      data.forEach(element => {
  
        this.x.push(element.x)
        this.y.push(element.y)
      });

      this.lineChartLabels =this.x;
      this.lineChartData =  [ { data: this.y , label: 'incidencias mensuales' },];
      });
  }

}

