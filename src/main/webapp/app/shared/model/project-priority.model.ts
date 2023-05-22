import { IProject } from '@/shared/model/project.model';

export interface IProjectPriority {
  id?: number;
  code?: string;
  priority?: string | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  projects?: IProject[] | null;
}

export class ProjectPriority implements IProjectPriority {
  constructor(
    public id?: number,
    public code?: string,
    public priority?: string | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public projects?: IProject[] | null
  ) {}
}
