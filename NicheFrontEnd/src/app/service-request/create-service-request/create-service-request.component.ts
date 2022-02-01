import { ServiceRequestService } from "../../services/service-request.service";
import { ServiceRequest } from "../../models/service-request";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-create-service-request",
  templateUrl: "./create-service-request.component.html",
  styleUrls: ["./create-service-request.component.css"],
})
export class CreateServiceRequestComponent implements OnInit {
  serviceRequest: ServiceRequest = new ServiceRequest();
  submitted = false;

  constructor(private ServiceRequestService: ServiceRequestService) {}

  ngOnInit() {}

  newServiceRequest(): void {
    this.submitted = false;
    this.serviceRequest = new ServiceRequest();
  }

  save() {
    this.ServiceRequestService.createServiceRequest(
      this.serviceRequest
    ).subscribe(
      (data) => console.log(data),
      (error) => console.log(error)
    );
    this.serviceRequest = new ServiceRequest();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
