import { ITask } from '@/shared/model/task.model';

export interface ITaskPriority {
  id?: number;
  code?: string;
  priority?: string | null;
  taskPriorityPictoContentType?: string | null;
  taskPriorityPicto?: string | null;
  notes?: string | null;
  tasks?: ITask[] | null;
}

export class TaskPriority implements ITaskPriority {
  constructor(
    public id?: number,
    public code?: string,
    public priority?: string | null,
    public taskPriorityPictoContentType?: string | null,
    public taskPriorityPicto?: string | null,
    public notes?: string | null,
    public tasks?: ITask[] | null
  ) {}
}
