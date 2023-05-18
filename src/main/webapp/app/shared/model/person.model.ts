import { ITask } from '@/shared/model/task.model';

export interface IPerson {
  id?: number;
  name?: string;
  code?: string | null;
  isExternal?: boolean | null;
  notes?: string | null;
  tasks?: ITask[] | null;
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string | null,
    public isExternal?: boolean | null,
    public notes?: string | null,
    public tasks?: ITask[] | null
  ) {
    this.isExternal = this.isExternal ?? false;
  }
}
