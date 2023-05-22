import { ITaskStatus } from '@/shared/model/task-status.model';
import { IMoM } from '@/shared/model/mo-m.model';
import { IProject } from '@/shared/model/project.model';
import { IPerson } from '@/shared/model/person.model';

export interface ITask {
  id?: number;
  subject?: string | null;
  taskTitle?: string;
  dealLine?: Date | null;
  isUrgent?: boolean | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  taskStatus?: ITaskStatus | null;
  taskMoM?: IMoM | null;
  projectName?: IProject | null;
  taskOwner?: IPerson | null;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public subject?: string | null,
    public taskTitle?: string,
    public dealLine?: Date | null,
    public isUrgent?: boolean | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public taskStatus?: ITaskStatus | null,
    public taskMoM?: IMoM | null,
    public projectName?: IProject | null,
    public taskOwner?: IPerson | null
  ) {
    this.isUrgent = this.isUrgent ?? false;
  }
}
