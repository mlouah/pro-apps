import { ITask } from '@/shared/model/task.model';

export interface ITaskStatus {
  id?: number;
  code?: string;
  status?: string | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  tasks?: ITask[] | null;
}

export class TaskStatus implements ITaskStatus {
  constructor(
    public id?: number,
    public code?: string,
    public status?: string | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public tasks?: ITask[] | null
  ) {}
}
