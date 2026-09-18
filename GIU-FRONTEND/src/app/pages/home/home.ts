import { Component } from '@angular/core';
import { Card } from '../../shared/molecule/card/card';

@Component({
  selector: 'app-home',
  imports: [Card],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {
  applications = [
    {
      name: 'APP1',
      description: 'Descripción APP1',
      icon: 'assets/icons/reschedule.svg'
    },
    {
      name: 'APP2',
      description: 'Descripción APP2',
      icon: 'assets/icons/reschedule.svg'
    },
    {
      name: 'APP3',
      description: 'Descripción APP3',
      icon: 'assets/icons/reschedule.svg'
    },
    {
      name: 'APP4',
      description: 'Descripción APP4',
      icon: 'assets/icons/reschedule.svg'
    },
    {
      name: 'APP5',
      description: 'Descripción APP5',
      icon: 'assets/icons/reschedule.svg'
    },
    {
      name: 'APP6',
      description: 'Descripción APP6',
      icon: 'assets/icons/reschedule.svg'
    }
  ];
}
