import { IProject } from '@/shared/model/project.model';

export interface IProjectCategory {
  id?: number;
  code?: string;
  category?: string | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  projects?: IProject[] | null;
}

export class ProjectCategory implements IProjectCategory {
  constructor(
    public id?: number,
    public code?: string,
    public category?: string | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public projects?: IProject[] | null
  ) {}
}
