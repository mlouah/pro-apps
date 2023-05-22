import { IProject } from '@/shared/model/project.model';

export interface ICompany {
  id?: number;
  name?: string;
  code?: string;
  companyProfile?: string | null;
  url?: string | null;
  logoContentType?: string | null;
  logo?: string | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  projects?: IProject[] | null;
}

export class Company implements ICompany {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string,
    public companyProfile?: string | null,
    public url?: string | null,
    public logoContentType?: string | null,
    public logo?: string | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public projects?: IProject[] | null
  ) {}
}
