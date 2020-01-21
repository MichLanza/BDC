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


  ngOnInit() {

     const  actualYear = new Date().getFullYear();
    
      this.statservice.getStats(actualYear).subscribe( (data) => {
        this.stat = data;
        let  x= [];
        let y= [];
        data.forEach(element => {
         x.push(element.x)
         y.push(element.y)
        });
        this.lineChartLabels = x;
        this.lineChartData =  [ { data: y , label: 'incidencias mensuales' },];
      });
    
  
      this.statservice.getStatsByPlat(actualYear).subscribe((data) =>{
        let ye = [];
        let equis = [];
        data.forEach(element => {
         equis.push(element.x)
         ye.push(element.y)
         });
         this.barChartData = [{ data: ye , label: 'Incidencias por plataforma' }];
         this.barChartLabels = equis;
      });

      this.statservice.getStatsByArea(actualYear).subscribe((data) =>{
        let ye = [];
        let equis = [];
        data.forEach(element => {
         equis.push(element.x)
         ye.push(element.y)
         });
         
         this.barChartData2 = [{ data: ye , label: 'Incidencias por Area' }];
         this.barChartLabels2 = equis;
      });

      this.statservice.getStatsBySol(actualYear).subscribe((data) =>{
        let ye = [];
        let equis = [];
        data.forEach(element => {
         equis.push(element.x)
         ye.push(element.porc)
         });
         
         this.pieChartData =   ye ;
         this.pieChartLabels = equis;
      });
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
  barChartData: ChartDataSets[] = [ { data: [0, 0, 0, 0, 0, 0], label: 'Best Fruits' } ];
 
  barChartOptions2: ChartOptions = {responsive: true,};
  barChartLabels2: Label[] = ['Apple', 'Banana', 'Kiwifruit', 'Blueberry', 'Orange', 'Grapes'];
  barChartType2: ChartType = 'bar';
  barChartLegend2 = true;
  barChartPlugins2 = [];
  barChartData2: ChartDataSets[] = [ { data: [0, 0, 0, 0, 0, 0], label: 'Best Fruits' } ];
  //barChartColor: Color = { backgroundColor: 'rgba(13,10,178,0.44)'}
 
 
  doughnutChartLabels: Label[] = ['BMW', 'Ford', 'Tesla'];
  doughnutChartData: MultiDataSet = [ [55, 25, 20] ];
  doughnutChartType: ChartType = 'doughnut';



  data(year:number){
    
 
    this.statservice.getStats(year).subscribe( (data) => {
      this.stat = data;
      let  x= [];
      let y= [];
      data.forEach(element => {
       x.push(element.x)
       y.push(element.y)
      });
      this.lineChartLabels = x;
      this.lineChartData =  [ { data: y , label: 'incidencias mensuales' },];
    });
  

    this.statservice.getStatsByPlat(year).subscribe((data) =>{
      let y = [];
      let x = [];
      data.forEach(element => {
       x.push(element.x)
       y.push(element.y)
       });
       this.barChartData = [{ data: y , label: 'Incidencias por plataforma' }];
       this.barChartLabels = x;
    });

    this.statservice.getStatsByArea(year).subscribe((data) =>{
      let y = [];
      let x = [];
      data.forEach(element => {
       x.push(element.x)
       y.push(element.y)
       });
       
       this.barChartData2 = [{ data: y , label: 'Incidencias por Area' }];
       this.barChartLabels2 = x;
    });

    this.statservice.getStatsBySol(year).subscribe((data) =>{
      let ye = [];
      let equis = [];
      data.forEach(element => {
       equis.push(element.x)
       ye.push(element.porc)
       });
       
       this.pieChartData =   ye ;
       this.pieChartLabels = equis;
    });

  }

}

