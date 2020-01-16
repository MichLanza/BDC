import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AddIncComponent } from './add-inc/add-inc.component';
import { ReadTableComponent } from './read-table/read-table.component';
import { ModIncidenciaComponent } from './mod-incidencia/mod-incidencia.component';
import { StatisticsComponent } from './statistics/statistics.component';


const routes: Routes = [
{ path:'', redirectTo: 'home', pathMatch: "full" },
{ path:'home', component: HomeComponent },
{ path:'add', component: AddIncComponent },
{ path: 'readT',component: ReadTableComponent},
{ path: 'mod',component: ModIncidenciaComponent},
{path: 'stats', component: StatisticsComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
