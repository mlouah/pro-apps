import { ITaskPriority } from '@/shared/model/task-priority.model';
import { ITaskStatus } from '@/shared/model/task-status.model';
import { IPerson } from '@/shared/model/person.model';
import { IMoM } from '@/shared/model/mo-m.model';
import { IProject } from '@/shared/model/project.model';

export interface ITask {
  id?: number;
  subject?: string | null;
  title?: string;
  dealLine?: Date | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  taskPriority?: ITaskPriority | null;
  taskStatus?: ITaskStatus | null;
  taskOwner?: IPerson | null;
  taskMoM?: IMoM | null;
  projectName?: IProject | null;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public subject?: string | null,
    public title?: string,
    public dealLine?: Date | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public taskPriority?: ITaskPriority | null,
    public taskStatus?: ITaskStatus | null,
    public taskOwner?: IPerson | null,
    public taskMoM?: IMoM | null,
    public projectName?: IProject | null
  ) {}
}
