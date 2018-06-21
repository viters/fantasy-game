import { LayoutModule } from '@angular/cdk/layout';
import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatCardModule,
  MatDialogModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatSelectModule,
  MatSidenavModule,
  MatSnackBarModule,
  MatTableModule,
  MatToolbarModule,
} from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { NavComponent } from './components/nav/nav.component';
import { LoginComponent } from './components/login/login.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRouter } from './app.router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './components/register/register.component';
import { HttpErrorInterceptor } from './services/http-error.interceptor';
import { AuthTokenInterceptor } from './services/auth-token.interceptor';
import { AuthService } from './services/auth.service';
import { CategoriesComponent } from './components/categories/categories.component';
import { CategoryFormComponent } from './components/categories/category-form/category-form.component';
import { CategoryMetadataPipe } from './pipes/category-metadata.pipe';
import { ElementsComponent } from './components/elements/elements.component';
import { ElementFormComponent } from './components/elements/elements-form/element-form.component';
import { MetadataComponent } from './components/metadata/metadata.component';
import { MetadataFormComponent } from './components/metadata/metadata-form/metadata-form.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    DashboardComponent,
    RegisterComponent,
    CategoriesComponent,
    CategoryFormComponent,
    CategoryMetadataPipe,
    ElementsComponent,
    ElementFormComponent,
    MetadataComponent,
    MetadataFormComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatTableModule,
    MatDialogModule,
    MatListModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppRouter,
  ],
  providers: [
    AuthService,
    {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: AuthTokenInterceptor, multi: true},
  ],
  entryComponents: [
    CategoryFormComponent,
    ElementFormComponent,
    MetadataFormComponent,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
