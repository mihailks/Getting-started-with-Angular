import {Component, OnInit, Input, EventEmitter, Output} from "@angular/core";
import {HousingLocation} from "../housing-location";

@Component({
  selector: 'app-housing-list',
  templateUrl: './housing-list.component.html',
  styleUrls: ['./housing-list.component.css']
})
export class HousingListComponent implements OnInit {
  @Input() locationList: HousingLocation[] = [];
  results: HousingLocation[] = [];
  @Output() selectedLocationEvent = new EventEmitter<HousingLocation>();

  constructor() {
  }

  ngOnInit(): void {
  }

  searchHousingLocations(searchText: string) {
    if (!searchText){
      return;
    }
    this.results = this.locationList
      .filter(
        l => l.city.toLowerCase().includes(searchText.toLowerCase())
      )
  }
  selectHousingLocation(location: HousingLocation) {
    this.selectedLocationEvent.emit(location);
  }
}
