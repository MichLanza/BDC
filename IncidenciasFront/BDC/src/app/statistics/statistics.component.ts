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
      
         this.doughnutChartData =  [ye];
         this.doughnutChartLabels = equis;
      });

      this.statservice.getStatsByArea(actualYear).subscribe((data) =>{
        let ye = [];
        let equis = [];

        data.forEach(element => {
         equis.push(element.x)
         ye.push(element.y)
         ye.push(0)
         equis.push('')
         });
         
         this.barChartData2 = [{ data: ye , label: 'Incidencias por Area' }];
         this.barChartLabels2 = equis;
      });

      this.statservice.getStatsByC(actualYear).subscribe((data) =>{
        let ye = [];
        let equis = [];
        let yeeS = [];

        data.forEach(element => {
         equis.push(element.x)
         ye.push(element.y)
         yeeS.push(element.yS)
         
         });
         
         this.barChartData = [ { data: ye, label: 'Resuelto' },{ data: yeeS, label: ' Por solucionar' } ];
         this.barChartLabels = equis;
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
  public pieChartLabels: Label[] = ['', '', '', '',''];
  public pieChartData: SingleDataSet = [0, 0, 0, 0,0];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];


  barChartOptions: ChartOptions = {responsive: true,};
  barChartLabels: Label[] = ['a', 'b', 'c', 'd', 'e', 'f'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  barChartData: ChartDataSets[] = [ { data: [0, 0, 0,0, 0, 0], label: 'Resuelto' },{ data: [0,0, 0, 0,0], label: ' Por solucionar' } ];
 
  barChartOptions2: ChartOptions = {responsive: true,};
  barChartLabels2: Label[] = ['', '', '', '', '', ''];
  barChartType2: ChartType = 'bar';
  barChartLegend2 = true;
  barChartPlugins2 = [];
  barChartData2: ChartDataSets[] = [ { data: [0, 0, 0, 0, 0, 0], label: ' ' } ];
  //barChartColor: Color = { backgroundColor: 'rgba(13,10,178,0.44)'}
 
 
  doughnutChartLabels: Label[] = ['', '', ''];
  doughnutChartData: MultiDataSet = [ [0, 0, 0] ];
  doughnutChartType: ChartType = 'doughnut';



  data(year:number ){
    
 
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
       this.doughnutChartData =  [y];
         this.doughnutChartLabels = x;
    });

    this.statservice.getStatsByArea(year).subscribe((data) =>{
      let y = [];
      let x = [];
      data.forEach(element => {
       x.push(element.x)
       y.push(element.y)
       y.push(0)
       x.push('')
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

    this.statservice.getStatsByC(year).subscribe((data) =>{
      let ye = [];
      let equis = [];
      let yeeS = [];

      data.forEach(element => {
       equis.push(element.x)
       ye.push(element.y)
       yeeS.push(element.yS)
       
       });
       
       this.barChartData = [ { data: ye, label: 'Resuelto' },{ data: yeeS, label: ' Por solucionar' } ];
       this.barChartLabels = equis;
    });

  }

}

