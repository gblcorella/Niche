import { ServiceRequestDetailsComponent } from "../service-request-details/service-request-details.component";
import { Observable } from "rxjs";
import { ServiceRequestService } from "../../services/service-request.service";
import { ServiceRequest } from "../../models/service-request";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-service-request-list",
  templateUrl: "./service-request-list.component.html",
  styleUrls: ["./service-request-list.component.css"],
})
export class ServiceRequestListComponent implements OnInit {
  serviceRequests: Observable<ServiceRequest[]>;

  constructor(
    private serviceRequestService: ServiceRequestService,
    private router: Router
  ) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.serviceRequests = this.serviceRequestService.getServiceRequestsList();
  }

  deleteServiceRequest(id: number) {
    this.serviceRequestService.deleteServiceRequest(id).subscribe(
      (data) => {
        console.log(data);
        this.reloadData();
      },
      (error) => console.log(error)
    );
  }
}
