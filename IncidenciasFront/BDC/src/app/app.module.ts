import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { AddIncComponent } from './add-inc/add-inc.component';
import { ReadTableComponent } from './read-table/read-table.component';
import { ModIncidenciaComponent } from './mod-incidencia/mod-incidencia.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SidebarComponent } from './header/sidebar.component';
import { ReadPipe } from './read-table/Pipes/read.pipe';
import { ToastrModule } from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { RangoFechasPipe } from './read-table/Pipes/rango-fechas.pipe';
import { FilterNombrePipe } from './read-table/Pipes/filter-nombre.pipe';
import { StatisticsComponent } from './statistics/statistics.component';
import { ChartsModule } from 'ng2-charts';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddIncComponent,
    ReadTableComponent,
    ModIncidenciaComponent,
    SidebarComponent,
    ReadPipe,
    RangoFechasPipe,
    FilterNombrePipe,
    StatisticsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      preventDuplicates: true,
      maxOpened: 1,
      autoDismiss: true,
      positionClass: "toast-top-right"
  }),
  NgbModule,
  ChartsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
