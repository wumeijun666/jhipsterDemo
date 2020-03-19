export interface IBird {
  id?: number;
  name?: string;
  add?: string;
  age?: number;
  seven?: number;
}

export class Bird implements IBird {
  constructor(public id?: number, public name?: string, public add?: string, public age?: number, public seven?: number) {}
}
