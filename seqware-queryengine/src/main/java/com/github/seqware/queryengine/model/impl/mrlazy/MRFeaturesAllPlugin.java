/*
 * Copyright (C) 2012 SeqWare
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.seqware.queryengine.model.impl.mrlazy;

import com.github.seqware.queryengine.factory.CreateUpdateManager;
import com.github.seqware.queryengine.factory.SWQEFactory;
import com.github.seqware.queryengine.model.Feature;
import com.github.seqware.queryengine.model.FeatureSet;
import com.github.seqware.queryengine.plugins.AnalysisPluginInterface;
import com.github.seqware.queryengine.plugins.MapReducePlugin;
import com.github.seqware.queryengine.tutorial.MapReduceFeaturesAll;

/**
 *
 * @author dyuen
 */
public class MRFeaturesAllPlugin extends MapReducePlugin<Feature, FeatureSet> {

    private FeatureSet outputSet;
    private CreateUpdateManager manager;
    
    @Override
    public AnalysisPluginInterface.ReturnValue init(FeatureSet inputSet, Object... parameters) {
        this.manager = SWQEFactory.getModelManager();
        //output outputSet should attach to the original reference
        outputSet = manager.buildFeatureSet().setReferenceID(inputSet.getReferenceID()).build();
        this.inputSet = inputSet;
        manager.close();
        return new AnalysisPluginInterface.ReturnValue();
    }

    @Override
    public AnalysisPluginInterface.ReturnValue test() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public AnalysisPluginInterface.ReturnValue verifyParameters() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    @Override
    public AnalysisPluginInterface.ReturnValue verifyInput() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override    
    public AnalysisPluginInterface.ReturnValue filterInit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override    
    public AnalysisPluginInterface.ReturnValue filter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public AnalysisPluginInterface.ReturnValue mapInit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AnalysisPluginInterface.ReturnValue reduceInit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override    
    public AnalysisPluginInterface.ReturnValue verifyOutput() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AnalysisPluginInterface.ReturnValue cleanup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FeatureSet getFinalResult() {
        MapReduceFeaturesAll process = new MapReduceFeaturesAll();
        process.processForFeatureSet(inputSet, outputSet);
        // after processing, outputSet will actually have been versioned several times, we need the latest one
        FeatureSet latestAtomBySGID = SWQEFactory.getQueryInterface().getLatestAtomBySGID(outputSet.getSGID(), FeatureSet.class);
        return latestAtomBySGID;
    }

    @Override
    public ReturnValue map(Feature atom, FeatureSet mappedSet) {
       // doesn't really do anything
        return new AnalysisPluginInterface.ReturnValue();
    }

    @Override
    public ReturnValue reduce(FeatureSet mappedSet, FeatureSet resultSet) {
        // doesn't really do anything
        return new AnalysisPluginInterface.ReturnValue();
    }
}