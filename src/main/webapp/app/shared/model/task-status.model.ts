import { ITask } from '@/shared/model/task.model';

export interface ITaskStatus {
  id?: number;
  code?: string;
  status?: string | null;
  statusPictoContentType?: string | null;
  statusPicto?: string | null;
  notes?: string | null;
  tasks?: ITask[] | null;
}

export class TaskStatus implements ITaskStatus {
  constructor(
    public id?: number,
    public code?: string,
    public status?: string | null,
    public statusPictoContentType?: string | null,
    public statusPicto?: string | null,
    public notes?: string | null,
    public tasks?: ITask[] | null
  ) {}
}
