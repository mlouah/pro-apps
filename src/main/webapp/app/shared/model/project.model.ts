import { IMoM } from '@/shared/model/mo-m.model';
import { ITask } from '@/shared/model/task.model';

export interface IProject {
  id?: number;
  name?: string;
  code?: string | null;
  notes?: string | null;
  isInternal?: boolean | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  moMTitles?: IMoM[] | null;
  tasks?: ITask[] | null;
}

export class Project implements IProject {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string | null,
    public notes?: string | null,
    public isInternal?: boolean | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public moMTitles?: IMoM[] | null,
    public tasks?: ITask[] | null
  ) {
    this.isInternal = this.isInternal ?? false;
  }
}
