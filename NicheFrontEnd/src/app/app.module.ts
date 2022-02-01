import { UserModule } from "./user/user.module";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { CreateServiceRequestComponent } from "./service-request/create-service-request/create-service-request.component";
import { ServiceRequestDetailsComponent } from "./service-request/service-request-details/service-request-details.component";
import { ServiceRequestListComponent } from "./service-request/service-request-list/service-request-list.component";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FooterComponent } from "./core/components/layout/footer/footer.component";
import { HeaderComponent } from "./core/components/layout/header/header.component";
import { LandingComponent } from "./core/components/layout/landing/landing.component";
import { CardComponent } from "./core/components/layout/card/card.component";
import { AboutusComponent } from "./core/components/layout/aboutus/aboutus.component";
import { AuthInterceptor } from "./services/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    CreateServiceRequestComponent,
    ServiceRequestDetailsComponent,
    ServiceRequestListComponent,
    FooterComponent,
    HeaderComponent,
    LandingComponent,
    CardComponent,
    AboutusComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    UserModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
