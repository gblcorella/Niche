import { ServiceRequest } from "../../models/service-request";
import { Component, OnInit, Input } from "@angular/core";
import { ServiceRequestService } from "../../services/service-request.service";
import { ServiceRequestListComponent } from "../service-request-list/service-request-list.component";

@Component({
  selector: "app-service-request-details",
  templateUrl: "./service-request-details.component.html",
  styleUrls: ["./service-request-details.component.css"],
})
export class ServiceRequestDetailsComponent implements OnInit {
  @Input() serviceRequest: ServiceRequest;

  constructor(
    private serviceRequestService: ServiceRequestService,
    private serviceRequestListComponent: ServiceRequestListComponent
  ) {}

  ngOnInit() {}
}
