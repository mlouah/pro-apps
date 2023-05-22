import { ITask } from '@/shared/model/task.model';
import { IProject } from '@/shared/model/project.model';

export interface IPerson {
  id?: number;
  name?: string;
  code?: string | null;
  isExternal?: boolean | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  tasks?: ITask[] | null;
  projects?: IProject[] | null;
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string | null,
    public isExternal?: boolean | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public tasks?: ITask[] | null,
    public projects?: IProject[] | null
  ) {
    this.isExternal = this.isExternal ?? false;
  }
}
